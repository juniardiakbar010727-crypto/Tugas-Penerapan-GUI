public class SkemaPrioritasTabungan extends ManajemenGaji {
    public SkemaPrioritasTabungan(double gajiBulanan) {
        // Pokok 35%, Investasi 25%, Dana Darurat 20%, Kirim Orang Tua 15%, Hiburan 5%
        super(gajiBulanan, 35.0, 25.0, 20.0, 15.0, 5.0);
    }

    @Override
    public String getNamaSkema() {
        return "Skema Prioritas Tabungan & Darurat";
    }

    @Override
    public String buatLaporan() {
        return super.buatLaporan() +
               "\n----------------------------------------" +
               "\nSaran: Fokus memperbesar aset investasi dan bantalan dana darurat.";
    }
}