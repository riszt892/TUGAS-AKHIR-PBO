package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class ModelTransaksi extends DbConnect{
    final String GET_COUNTER = "select * from counter_record where id_record=1;";
    final String SET_COUNTER = "update counter_record set record_transaksi= ? where id_record=1;";
    final String ADD_TRANSAKSI = "insert into transaksi (id_produk,jumlah,record_transaksi) values (?,?,?)";
    final String SHOW_ALL_TRANSAKSI ="select * from transaksi";
    final String CETAK_STRUK = "select * from transaksi where record_transaksi=?;";
    final String GET_PRODUK = "select * from produk where id_produk =?";
    private final String GET_KEMASAN = "select * from kemasan";
    public ModelTransaksi() throws SQLException {
        super();
    }

    public int getRecord() throws SQLException {
        int numRecords=0;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_COUNTER);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            numRecords = rs.getInt("record_transaksi");

        }
        return numRecords;

    }
    public void setRecord() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SET_COUNTER);
        int counter = getRecord() + 1;
        preparedStatement.setInt(1,counter);
        preparedStatement.executeUpdate();

    }
    public void addTransaksi(int id_produk,int jumlah) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_TRANSAKSI);
        preparedStatement.setInt(1,id_produk);
        preparedStatement.setInt(2,jumlah);
        preparedStatement.setInt(3,getRecord());

        preparedStatement.executeUpdate();
    }
    public void showTransaksi() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUK);
        PreparedStatement transaksi = connection.prepareStatement(SHOW_ALL_TRANSAKSI);



        Locale indo = new Locale("in","ID");
        Currency rupiah = Currency.getInstance(indo);
        NumberFormat format_rupiah = NumberFormat.getCurrencyInstance(indo);


        ResultSet rs_transaksi = transaksi.executeQuery();

        System.out.println("=====================================================================================================================================");
        System.out.printf("| %-3s| %-10s | %-11s | %-30s| %-10s| %-20s| %-10s| %-20s|%n","No","ID Produk","ID Struk","Nama","Kemasan","Harga","Jumlah","Total");
        System.out.println("=====================================================================================================================================");

        int num = 1;
        while (rs_transaksi.next()){
            preparedStatement.setInt(1,rs_transaksi.getInt("id_produk"));
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){

                int total =rs_transaksi.getInt("jumlah")*rs.getInt("harga");

                System.out.printf("| %-3s| %-10s | %-11s | %-30s| %-10s| %-20s| %-10s| %-20s|%n",String.valueOf(num),String.valueOf(rs.getInt("id_produk")),String.valueOf(rs_transaksi.getInt("record_transaksi")),rs.getString("nama"),getKemasan(rs.getInt("id_kemasan")),format_rupiah.format(rs.getInt("harga")),String.valueOf(rs_transaksi.getInt("jumlah")),format_rupiah.format(total));
                num++;
            }
        }
    }

    public void cetakStruk(int record_transaksi) throws SQLException {
        int totalBayar = 0;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUK);
        PreparedStatement transaksi = connection.prepareStatement(CETAK_STRUK);
        transaksi.setInt(1,record_transaksi);




        Locale indo = new Locale("in","ID");
        Currency rupiah = Currency.getInstance(indo);
        NumberFormat format_rupiah = NumberFormat.getCurrencyInstance(indo);


        ResultSet rs_transaksi = transaksi.executeQuery();

        System.out.println("=====================================================================================================================================");
        System.out.printf("| %-3s| %-10s | %-30s| %-10s| %-20s| %-10s| %-20s|%n","No","ID Produk","Nama","Kemasan","Harga","Jumlah","Total");
        System.out.println("=====================================================================================================================================");

        int num = 1;
        while (rs_transaksi.next()){
            preparedStatement.setInt(1,rs_transaksi.getInt("id_produk"));
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){

                int total =rs_transaksi.getInt("jumlah")*rs.getInt("harga");
                totalBayar += total;

                System.out.printf("| %-3s| %-10s | %-30s| %-10s| %-20s| %-10s| %-20s|%n",String.valueOf(num),String.valueOf(rs.getInt("id_produk")),rs.getString("nama"),getKemasan(rs.getInt("id_kemasan")),format_rupiah.format(rs.getInt("harga")),String.valueOf(rs_transaksi.getInt("jumlah")),format_rupiah.format(total));
                num++;
            }
        }
        System.out.println();
        System.out.println("Total Bayar : "+format_rupiah.format(totalBayar));
    }

    public String getKemasan(int id_kemasan) throws SQLException {
        String ukuran = "";
        PreparedStatement preparedStatement = connection.prepareStatement(GET_KEMASAN);
        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()){
            if(id_kemasan==rs.getInt("id_kemasan")){
                ukuran = rs.getString("ukuran");
            }
        }
        return ukuran;
    }


}
