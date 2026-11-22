public class ManajemenGaji {
    private double gajiBulanan;
    private double persenKebutuhanPokok;
    private double persenInvestasi;
    private double persenDanaDarurat;
    private double persenKirimOrangTua;
    private double persenHiburan;

    public ManajemenGaji(
            double gajiBulanan,
            double persenKebutuhanPokok,
            double persenInvestasi,
            double persenDanaDarurat,
            double persenKirimOrangTua,
            double persenHiburan
    ) {
        setGajiBulanan(gajiBulanan);
        setPersentase(
            persenKebutuhanPokok,
            persenInvestasi,
            persenDanaDarurat,
            persenKirimOrangTua,
            persenHiburan
        );
    }

    public double getGajiBulanan() {
        return gajiBulanan;
    }

    public void setGajiBulanan(double gajiBulanan) {
        if (gajiBulanan <= 0) {
            throw new IllegalArgumentException("Gaji bulanan harus lebih besar dari 0.");
        }
        this.gajiBulanan = gajiBulanan;
    }

    public void setPersentase(
            double pokok,
            double investasi,
            double darurat,
            double orangTua,
            double hiburan
    ) {
        double total = pokok + investasi + darurat + orangTua + hiburan;
        if (pokok < 0 || investasi < 0 || darurat < 0 || orangTua < 0 || hiburan < 0) {
            throw new IllegalArgumentException("Persentase tiap pos tidak boleh negatif.");
        }
        if (Math.abs(total - 100.0) > 0.001) {
            throw new IllegalArgumentException("Total persentase alokasi harus tepat 100% (saat ini: " + total + "%).");
        }
        this.persenKebutuhanPokok = pokok;
        this.persenInvestasi = investasi;
        this.persenDanaDarurat = darurat;
        this.persenKirimOrangTua = orangTua;
        this.persenHiburan = hiburan;
    }

    public double nominalPokok() {
        return gajiBulanan * (persenKebutuhanPokok / 100.0);
    }

    public double nominalInvestasi() {
        return gajiBulanan * (persenInvestasi / 100.0);
    }

    public double nominalDarurat() {
        return gajiBulanan * (persenDanaDarurat / 100.0);
    }

    public double nominalOrangTua() {
        return gajiBulanan * (persenKirimOrangTua / 100.0);
    }

    public double nominalHiburan() {
        return gajiBulanan * (persenHiburan / 100.0);
    }

    public String getNamaSkema() {
        return "Skema Standar";
    }

    public String buatLaporan() {
        return String.format(
            "=== %s ===%n" +
            "Total Gaji Bulanan : Rp %,.0f%n" +
            "----------------------------------------%n" +
            "Kebutuhan Pokok (%.0f%%) : Rp %,.0f%n" +
            "Investasi       (%.0f%%) : Rp %,.0f%n" +
            "Dana Darurat    (%.0f%%) : Rp %,.0f%n" +
            "Kirim Orang Tua (%.0f%%) : Rp %,.0f%n" +
            "Hiburan         (%.0f%%) : Rp %,.0f",
            getNamaSkema(),
            gajiBulanan,
            persenKebutuhanPokok, nominalPokok(),
            persenInvestasi, nominalInvestasi(),
            persenDanaDarurat, nominalDarurat(),
            persenKirimOrangTua, nominalOrangTua(),
            persenHiburan, nominalHiburan()
        );
    }
}