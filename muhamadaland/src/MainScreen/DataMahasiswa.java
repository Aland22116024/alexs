package MainScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DataMahasiswa extends JFrame{
    private JPanel PanelMain;
    private JList JlistMahasiswa;
    private JButton filterButton;
    private JTextField textFieldFilter;
    private JTextField textFieldNama;
    private JTextField textFieldNim;
    private JTextField textFieldIpk;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton clearButton;

    private List<Mahasiswa> arraydata = new ArrayList<>();

    private DefaultListModel defaultListModel = new DefaultListModel<>();

    class Mahasiswa{
        private String nama;
        private String nim;

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getNim() {
            return nim;
        }

        public void setNim(String nim) {
            this.nim = nim;
        }

        public float getIpk() {
            return ipk;
        }

        public void setIpk(float ipk) {
            this.ipk = ipk;
        }

        private float ipk;
    }

    public DataMahasiswa(){
        super("Data Mahasiswa");
        this.setContentPane(PanelMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nama = textFieldNama.getText();
                String nim = textFieldNim.getText();
                Float ipk = Float.valueOf(textFieldIpk.getText());

                Mahasiswa mahasiswa = new Mahasiswa();

                mahasiswa.setNama(nama);
                mahasiswa.setNim(nim);
                mahasiswa.setIpk(ipk);

                arraydata.add(mahasiswa);

                setFilterButton();
                fromMahasiswaToListMode();
            }
        });
        JlistMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int index = JlistMahasiswa.getSelectedIndex();

                if (index < 0 )
                return;

                String nama = JlistMahasiswa.getSelectedValue().toString();

                for (int i = 0; i < arraydata.size(); i++) {
                    if (arraydata.get(i).getNama().equals(nama)){
                        Mahasiswa mahasiswa = arraydata.get(i);
                        textFieldIpk.setText(String.valueOf(mahasiswa.getIpk()));
                        textFieldNama.setText(mahasiswa.getNama());
                        textFieldNim.setText(mahasiswa.getNim());
                        break;
                    }

                }
            }
        });

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = textFieldFilter.getText();

                List<String> filtered = new ArrayList<>();

                for (int i = 0; i < arraydata.size(); i++) {
                    if (arraydata.get(i).getNama().contains(keyword)){
                        filtered.add(arraydata.get(i).getNama());
                    }
                }
               refreshListModel(filtered);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = JlistMahasiswa.getSelectedIndex();

                if (index < 0)
                    return;

                String nama = JlistMahasiswa.getSelectedValue().toString();

                for (int i = 0; i < arraydata.size(); i++){
                    if (arraydata.get(i).getNama().equals(nama)){
                        arraydata.remove(i);
                        break;
                    }
                }
                setFilterButton();
                fromMahasiswaToListMode();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFilterButton();
            }
        });
    }

    private void fromMahasiswaToListMode(){

        List<String> listNamaMahasiswa = new ArrayList<>();

        for (int i = 0; i < arraydata.size(); i++) {
            listNamaMahasiswa.add(arraydata.get(i).getNama());
        }
        refreshListModel(listNamaMahasiswa);
    }

    private void setFilterButton(){
        textFieldNama.setText("");
        textFieldNim.setText("");
        textFieldIpk.setText("");
    }

    private void refreshListModel(List<String> list){
        defaultListModel.clear();
        defaultListModel.addAll(list);
        JlistMahasiswa.setModel(defaultListModel);
    }

    public static void main(String[] args) {
        DataMahasiswa dataMahasiswa = new DataMahasiswa();
        dataMahasiswa.setVisible(true);
    }
}
