import java.sql.*;

    public class AlunoAcademiaDatabase {
        public boolean postAlunoAcademia(AlunoAcademia aluno) throws Exception {

            String url = "jdbc:sqlserver://localhost:1433;databaseName=database;encrypt=true;trustServerCertificate=true;";
            String usuario = "john";
            String senha = "123456";
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url,usuario,senha);
            Statement st = con.createStatement();
            try {
                String sql = "CREATE TABLE Aluno (Id int IDENTITY(1,1) PRIMARY KEY, Nome varchar(255), Idade int, Peso float, Altura float, Objetivo varchar(255))";
                st.executeUpdate(sql);
            } catch (Exception e) {
            }
            String sql = String.format("INSERT INTO Aluno (Nome, Idade, Peso, Altura, Objetivo) Values ('%s', %s, %s, %s, '%s')",
                    aluno.getNome(),
                    aluno.getIdade(),
                    aluno.getPeso(),
                    aluno.getAltura(),
                    aluno.getObjetivo()
            );
            st.executeUpdate(sql);
            con.close();
            return true;
        }
    }