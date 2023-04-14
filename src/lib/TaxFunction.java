package lib;

public class TaxFunction {

    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
     *
     * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
     *
     * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
     * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
     * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
     *
     */

    public static double calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {

        double taxableIncome = calculateTaxableIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking, isMarried, numberOfChildren);

        double tax = 0.05 * (taxableIncome - deductible);

        if (tax < 0) {
            return 0;
        } else {
            return tax;
        }
    }

    /**
     * Fungsi untuk menghitung penghasilan kena pajak dari pegawai berdasarkan status pernikahan dan jumlah anak yang dimiliki.
     *
     */
    public static double calculateTaxableIncome(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, boolean isMarried, int numberOfChildren) {

        double nonTaxableIncome = 54000000;

        if (isMarried) {
            nonTaxableIncome += 4500000;
        }

        if (numberOfChildren > 0 && numberOfChildren <= 3) {
            nonTaxableIncome += numberOfChildren * 4500000;
        } else if (numberOfChildren > 3) {
            nonTaxableIncome += 3 * 4500000;
        }

        return (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking - nonTaxableIncome;
    }
}

