package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class ModelProduk extends DbConnect{
    private final String ADD_PRODUK = "insert into produk(nama,id_kemasan,harga,stok,status) values (?,?,?,?,?);";
    private final String SHOW_PRODUK = "select * from produk";
    private final String GET_KEMASAN = "select * from kemasan";
    private final String GET_JUMLAH = "select * from produk where id_produk = ?";
    private final String DELETE_PRODUK = "delete from produk where id_produk = ?";
    private final String UPDATE_JUMALAH_PRODUK = "update produk set stok=? where id_produk = ?";


    public ModelProduk() throws SQLException {
        super();
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

    public void addProduk(String nama,int id_kemasan,int harga,int stok,String status) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUK);

        preparedStatement.setString(1,nama);
        preparedStatement.setInt(2,id_kemasan);
        preparedStatement.setInt(3,harga);
        preparedStatement.setInt(4,stok);
        preparedStatement.setString(5,status);

        preparedStatement.executeUpdate();
    }
    public int getJumlah(int id) throws SQLException {
        int jumlah = 0;
        PreparedStatement getJumlah = connection.prepareStatement(GET_JUMLAH);
        getJumlah.setInt(1,id);
        ResultSet rs = getJumlah.executeQuery();

        while (rs.next()){
            jumlah = rs.getInt("stok");
        }
        return jumlah;
    }
    public void updateJumlah(int jumlah,int id_produk) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_JUMALAH_PRODUK);

        int jumlahSaatIni = getJumlah(id_produk);
        int jumlahSekarang = jumlahSaatIni - jumlah;
        preparedStatement.setInt(1,jumlahSekarang);
        preparedStatement.setInt(2,id_produk);
        preparedStatement.executeUpdate();
    }

    public void showProduk() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SHOW_PRODUK);

        Locale indo = new Locale("in","ID");
        Currency rupiah = Currency.getInstance(indo);
        NumberFormat format_rupiah = NumberFormat.getCurrencyInstance(indo);


        ResultSet rs = preparedStatement.executeQuery();

        System.out.println("==============================================================================================================");
        System.out.printf("| %-3s| %-10s  | %-30s| %-10s| %-20s| %-10s| %-10s|%n","No","ID Produk","Nama","Kemasan","Harga","Stok","Status");
        System.out.println("==============================================================================================================");
        int num = 1;

        while(rs.next()){


            System.out.printf("| %-3s| %-10s  | %-30s| %-10s| %-20s| %-10s| %-10s|%n",String.valueOf(num),String.valueOf(rs.getInt("id_produk")),rs.getString("nama"),getKemasan(rs.getInt("id_kemasan")),format_rupiah.format(rs.getInt("harga")),String.valueOf(rs.getInt("stok")),rs.getString("status"));
            num++;
        }
    }
     public void deleteProduk(int id_produk) throws SQLException {

         PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUK);
         preparedStatement.setInt(1,id_produk);

         preparedStatement.executeUpdate();



     }

}
