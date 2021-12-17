package Control;

import Model.ModelTransaksi;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Transaksi {
    private boolean add = true;
    private boolean addMore = true;
    private ModelTransaksi transaksi = new ModelTransaksi();
    private Produk produk = new Produk();
    private Scanner sc = new Scanner(System.in);
    private Scanner sc1 = new Scanner(System.in);

    public Transaksi() throws SQLException {
    }
    public void showTransaksi() throws SQLException {
        transaksi.showTransaksi();
    }

    public void cetakTransaksi() throws SQLException {
        System.out.println("CETAK STRUK");
        System.out.print("ID Struk : ");
        int record_transaksi = sc.nextInt();

        transaksi.cetakStruk(record_transaksi);
    }

    public void addTransaksi() throws SQLException {

        while (add){
            if(addMore){
                this.produk.showProduk();
                System.out.println("TAMBAH DATA TRANSAKSI");
                System.out.print("ID Produk : ");
                int id = sc.nextInt();
                System.out.print("Jumlah : ");
                int jumlah = sc.nextInt();
                this.transaksi.addTransaksi(id,jumlah);
                this.produk.updateJumlah(jumlah,id);
                System.out.println();
                System.out.print("Tambah barang lagi (Y/N) ? ");
                String tambahLagi = sc1.nextLine();

                if(!Objects.equals(tambahLagi, "Y")){
                    addMore = false;
                    transaksi.setRecord();
                }

            } else {

                add = false;
            }
        }
       }
}
