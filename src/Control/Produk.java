package Control;

import Model.ModelProduk;

import java.sql.SQLException;
import java.util.Scanner;

public class Produk {
    ModelProduk produk = new ModelProduk();
    Scanner sc = new Scanner(System.in);

    public Produk() throws SQLException {
    }
    public void showProduk() throws SQLException {
        produk.showProduk();
    }

    public void updateJumlah(int jumlah,int id) throws SQLException {
        this.produk.updateJumlah(jumlah,id);

    }

    public void deleteProduk() throws SQLException {
        this.produk.showProduk();
        System.out.println("HAPUS PRODUK");
        System.out.print("ID Produk : ");
        int id = sc.nextInt();
        produk.deleteProduk(id);
    }
    public void tambahProduk() throws SQLException {
        Scanner sc1 = new Scanner(System.in);
        System.out.println("TAMBAH PRODUK");
        System.out.print("Nama : ");
        String nama = sc.nextLine();
        System.out.println("KEMASAN");
        System.out.println("1. kecil\n2. sedang\n3. besar");
        System.out.print("Kemasan : ");
        int kemasan = sc.nextInt();
        System.out.print("Harga : ");
        int harga = sc.nextInt();
        System.out.print("Stok : ");
        int stok = sc.nextInt();
        System.out.print("Status : ");
        String status = sc1.nextLine();

        produk.addProduk(nama,kemasan,harga,stok,status);


    }
}
