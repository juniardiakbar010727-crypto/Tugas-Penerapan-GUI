import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManajemenGajiGUI {
    private JFrame frame;
    private JTextField txtGaji;
    private JComboBox<String> cmbSkema;
    private JButton btnHitung;
    private JButton btnReset;
    private JTextArea areaHasil;

    public ManajemenGajiGUI() {
        frame = new JFrame("Kalkulator Manajemen Gaji Bulanan");
        txtGaji = new JTextField(16);
        cmbSkema = new JComboBox<>(new String[]{
            "Skema Seimbang",
            "Skema Prioritas Tabungan"
        });
        btnHitung = new JButton("Hitung Alokasi");
        btnReset = new JButton("Reset");

        areaHasil = new JTextArea(12, 44);
        areaHasil.setEditable(false);
        areaHasil.setFont(new Font("Monospaced", Font.PLAIN, 13));
    }

    public void tampilkanGUI() {
        frame.setLayout(new BorderLayout(10, 10));

        JPanel panelAtas = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelAtas.add(new JLabel("Gaji Bulanan (Rp):"));
        panelAtas.add(txtGaji);
        panelAtas.add(new JLabel("Pilih Skema:"));
        panelAtas.add(cmbSkema);
        panelAtas.add(btnHitung);
        panelAtas.add(btnReset);

        frame.add(panelAtas, BorderLayout.NORTH);
        frame.add(new JScrollPane(areaHasil), BorderLayout.CENTER);

        // Registrasi Event Listener (ActionListener via inner class)
        HitungListener hitungListener = new HitungListener();
        btnHitung.addActionListener(hitungListener);
        txtGaji.addActionListener(hitungListener);
        btnReset.addActionListener(new ResetListener());

        // Contoh Adapter class (WindowAdapter) sesuai materi slide
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class HitungListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String inputMentah = txtGaji.getText().trim().replace(".", "").replace(",", "");
                double gaji = Double.parseDouble(inputMentah);

                String skema = (String) cmbSkema.getSelectedItem();
                ManajemenGaji perhitungan;

                if ("Skema Prioritas Tabungan".equals(skema)) {
                    perhitungan = new SkemaPrioritasTabungan(gaji);
                } else {
                    perhitungan = new SkemaSeimbang(gaji);
                }

                areaHasil.setText(perhitungan.buatLaporan());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    frame,
                    "Input gaji tidak valid. Masukkan angka saja (misalnya 3500000).",
                    "Kesalahan Input",
                    JOptionPane.ERROR_MESSAGE
                );
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                    frame,
                    ex.getMessage(),
                    "Validasi Data",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }

    private class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            txtGaji.setText("");
            areaHasil.setText("");
            cmbSkema.setSelectedIndex(0);
            txtGaji.requestFocusInWindow();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ManajemenGajiGUI gui = new ManajemenGajiGUI();
            gui.tampilkanGUI();
        });
    }
}
