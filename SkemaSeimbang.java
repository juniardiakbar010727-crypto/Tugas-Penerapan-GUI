public class SkemaSeimbang extends ManajemenGaji {
    public SkemaSeimbang(double gajiBulanan) {
        // Pokok 40%, Investasi 15%, Dana Darurat 15%, Kirim Orang Tua 20%, Hiburan 10%
        super(gajiBulanan, 40.0, 15.0, 15.0, 20.0, 10.0);
    }

    @Override
    public String getNamaSkema() {
        return "Skema Seimbang (40/15/15/20/10)";
    }

    @Override
    public String buatLaporan() {
        return super.buatLaporan() +
               "\n----------------------------------------" +
               "\nSaran: Menjaga keseimbangan pengeluaran rutin, tabungan, dan keluarga.";
    }
} 
