/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class DataBaseConnector {
        
    
        

	public Connection con = null;
	public Statement st = null;
	public ResultSet rs = null;
        
    
       
	//Função para conexão com o Banco de Dados
	public void conect() throws SQLException{
		String server = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false";
		String driver = "com.mysql.jdbc.Driver";
		
              
		try{
			
			Class.forName(driver);
			this.con = DriverManager.getConnection(server,System.getProperty("usuario"),System.getProperty("senha"));
			this.st = this.con.createStatement();
						
		}catch(ClassNotFoundException e){
			System.out.println("Erro no estabelecimento da conexão: " + e.getMessage());	
		}
                                                                          }	
	//Verifica se o Banco de Dados está conectado	
	public boolean isConect(){
	
            return this.con != null;
	                          }
	
	
	
	
	//Verifica se o BD está desconectado
	public void disconect(){
		
		try{
			this.con.close();
		   }catch(SQLException e){
			System.out.println("Erro" + e.getMessage());	
		                         }
                                }

 
    
    public void adicionaCliente(String entidade,String nome,String endereco){ 
        String sql = "INSERT INTO " + entidade  + "(nome,endereco) VALUES(?,?)";
        try { 
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1,nome);
                stmt.setString(2,endereco);
                stmt.execute();
            }
        } 
        catch (SQLException u) { 
            throw new RuntimeException(u);
        } 
        
    } 
    
    public void adicionaPedido(String entidade,float valor,String cliente,String desc,String data){ 
        String sql = "INSERT INTO " + entidade  + "(valor,data,codigoCliente,descricao) VALUES(?,?,?,?)";
        try { 
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setFloat(1,valor);
                stmt.setString(2,data);
                stmt.setString(3,cliente);
                stmt.setString(4,desc);
                stmt.execute();
            }
        } 
        catch (SQLException u) { 
            throw new RuntimeException(u);
        } 
        
    } 
    
     
     
        public void alteraPedido(float valor,String cliente,String desc,String data,String codigoPedido){ 
        String sql = "UPDATE pedidos SET valor = ?, data = ?, codigoCliente = ?, descricao = ? WHERE codigoPedidos = ?";
        try { 
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setFloat(1,valor);
                stmt.setString(2,data);
                stmt.setString(3,cliente);
                stmt.setString(4,desc);
                stmt.setString(5,codigoPedido);
                stmt.execute();
            }
        } 
        catch (SQLException u) { 
            throw new RuntimeException(u);
        } 
        
    } 


    public DefaultTableModel consultarTotalPedido(){
        DefaultTableModel mod = new DefaultTableModel();
        
        
        
        
        String sql = "SELECT * FROM pedidos;";
        mod.addColumn("Código Pedido");
        mod.addColumn("Código Cliente");
        mod.addColumn("Descrição");
        mod.addColumn("Valor");
        mod.addColumn("Data");
        
        

        try { 
                        conect();
                                
			this.st = con.createStatement();
                                

                        this.rs = this.st.executeQuery(sql);
                        
        
			while(this.rs.next()){
                            
        
                                mod.addRow(new Object[]{rs.getInt(1),
                                    rs.getInt(4),rs.getString(5),rs.getFloat(2),rs.getString(3) });

			}
        } 
        catch (SQLException u) { 
            throw new RuntimeException(u);
        } 
        
        return mod;
    }
    
    
    public DefaultTableModel consultarTotalPedidoCliente(String cliente){
        DefaultTableModel mod = new DefaultTableModel();
           
        
        String sql = "SELECT * FROM pedidos p inner join cliente c where c.codigoCliente = p.codigoCliente and c.codigoCliente = "+ cliente +";";
        mod.addColumn("Código Pedido");
        mod.addColumn("Descrição");
        mod.addColumn("Valor");
        mod.addColumn("Data");
        
        

        try { 
                        conect();
                                
			this.st = con.createStatement();
                                

                        this.rs = this.st.executeQuery(sql);
                        
        
			while(this.rs.next()){
                            
        
                                mod.addRow(new Object[]{rs.getInt(1),rs.getString(5),rs.getFloat(2),rs.getString(3) });

			}
        } 
        catch (SQLException u) { 
            throw new RuntimeException(u);
        } 
        
        return mod;
    }
    
    
    
     public float valorTotalPedido(){
         
        float valor = 0;
           
        
        String sql = "SELECT  SUM(valor) as valor FROM pedidos;";
        
        try { 
                        conect();
                        
			this.st = con.createStatement();
                                
                        this.rs = this.st.executeQuery(sql);
                        
                        if(rs.next()){
			valor = rs.getFloat("valor");
                        }
        } 
        catch (SQLException u) { 
            throw new RuntimeException(u);
        } 
        
        return valor;
    }
 
     
     
     public float valorTotalPedidoCliente(String cliente){
         
        float valor = 0;
           
        
        String sql = "SELECT  SUM(valor) as valor FROM pedidos where codigoCliente = "+ cliente +";";
        
        try { 
                        conect();
                        
			this.st = con.createStatement();
                                
                        this.rs = this.st.executeQuery(sql);
                        
                        if(rs.next()){
			valor = rs.getFloat("valor");
                        }
        } 
        catch (SQLException u) { 
            throw new RuntimeException(u);
        } 
       
        return valor;
    }
                   
   
    
    
}

