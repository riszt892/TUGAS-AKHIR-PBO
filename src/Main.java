import Control.Produk;
import Control.Transaksi;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public Main() throws SQLException {
    }

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        boolean run = true;
        Produk produk = new Produk();
        Transaksi transaksi = new Transaksi();
        try {
            while (run){
                System.out.println("=========================");
                System.out.println("|   SELAMAT DATANG DI   |");
                System.out.println("|       CIGAR.IN        |");
                System.out.println("=========================");
                System.out.println("1. Lihat Produk\n2. Kelola Produk\n3. Tambah Transaksi\n4. Lihat Riwayat Transaksi\n5. Cetak Struk\n6. Keluar");
                System.out.print("Masukkan pilihan : ");
                int pilihan = sc.nextInt();
                if (pilihan==1){
                    produk.showProduk();
                    System.out.print("Tekan enter untuk melanjutkan..");
                    sc1.nextLine();

                }else if(pilihan==2){
                    System.out.println("KELOLA PRODUK");
                    System.out.println("1. Tambah Produk\n2. Hapus Produk");
                    System.out.println("Masukkan pilihan : ");
                    int kelola = sc.nextInt();
                    if(kelola==1){
                        produk.tambahProduk();
                    }else if (kelola==2){
                        produk.deleteProduk();
                    }
                }else if(pilihan==3){
                    transaksi.addTransaksi();
                }else if(pilihan==4){
                    transaksi.showTransaksi();
                    System.out.print("Tekan enter untuk melanjutkan..");
                    sc1.nextLine();

                }else if(pilihan==5){
                    transaksi.showTransaksi();
                    transaksi.cetakTransaksi();
                    System.out.print("Tekan enter untuk melanjutkan..");
                    sc1.nextLine();

                } else if(pilihan==6){
                    run=false;
                }
            }



        }catch (InputMismatchException e){
            System.out.println("Mohon maaf inputan salah");

        }

    }
}
