
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Model;
import view.View;

public class Controller {

    Model model;
    View view;
    
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           
            if (e.getSource() == view.jb_primero) {
                try {
                    jb_primero_actionPerformed();
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == view.jb_anterior) {
                try {
                    jb_anterior_actionPerformed();
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == view.jb_siguiente) {
                try {
                    jb_siguiente_actionPerformed();
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == view.jb_ultimo) {
                try {
                    jb_ultimo_actionPerformed();
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(e.getSource() == view.jb_eliminar){
                try {
                    jb_eliminar();
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(e.getSource() == view.jb_insertar){
                try {
                    jb_insertar();
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(e.getSource() == view.jb_modificar){
                try {
                    jb_modificar();
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(e.getSource() == view.jb_nuevo){
                jb_nuevo();
            }

        }

    };

    public Controller(Model modelAgenda, View viewAgenda) {
        this.model = modelAgenda;
        this.view = viewAgenda;
        initComponents();
        setActionListener();
        initDB();
    }

    public void initDB(){
        model.conectarDB();
        view.jtf_nombre.setText(model.getNombre());
        view.jtf_email.setText(model.getEmail());
    }

    public void initComponents() {
        view.setLocationRelativeTo(null);
        view.setTitle("Agenda MVC");
        view.setVisible(true);
    }


    public void setActionListener() {
        view.jb_primero.addActionListener(actionListener);
        view.jb_anterior.addActionListener(actionListener);
        view.jb_siguiente.addActionListener(actionListener);
        view.jb_ultimo.addActionListener(actionListener);
        view.jb_eliminar.addActionListener(actionListener);
        view.jb_insertar.addActionListener(actionListener);
        view.jb_modificar.addActionListener(actionListener);
        view.jb_nuevo.addActionListener(actionListener);
    }

 
    private void jb_primero_actionPerformed() throws SQLException {
        System.out.println("Action del boton jb_primero");
        model.primerRegistro();
        view.jtf_nombre.setText(model.getNombre());
        view.jtf_email.setText(model.getEmail());

    }


    private void jb_anterior_actionPerformed() throws SQLException {
        System.out.println("Action del boton jb_anterior");
        model.anteriorRegistro();
        view.jtf_nombre.setText(model.getNombre());
        view.jtf_email.setText(model.getEmail());
    }


    private void jb_ultimo_actionPerformed() throws SQLException {
        System.out.println("Action del boton jb_ultimo");
        model.ultimoRegistro();
        view.jtf_nombre.setText(model.getNombre());
        view.jtf_email.setText(model.getEmail());
 
    }
    private void jb_siguiente_actionPerformed() throws SQLException {
        System.out.println("Action del boton jb_siguiente");
        model.siguienteRegistro();
        view.jtf_nombre.setText(model.getNombre());
        view.jtf_email.setText(model.getEmail());
       
  
    }
    private void jb_eliminar() throws SQLException {
            System.out.println("Action del boton jb_eliminar");
            System.out.println(model.getEmail());
            model.eliminarRegistro(model.getEmail());
            JOptionPane.showMessageDialog(view, "Registro eliminado correctamente");
            jb_primero_actionPerformed();
        }

        private void jb_insertar() throws SQLException {
            System.out.println("Action del boton jb_insertar");
            model.setNombre(view.jtf_nombre.getText());
            model.setEmail(view.jtf_email.getText());
            model.insertarRegistro(model.getNombre(),model.getEmail());
            JOptionPane.showMessageDialog(view, "Registro guardado correctamente");
            jb_primero_actionPerformed();
        }

        private void jb_modificar() throws SQLException {
            System.out.println("Action del boton jb_modificar");
            model.modificarRegistro(view.jtf_nombre.getText(), view.jtf_email.getText());
            JOptionPane.showMessageDialog(view, "Registro actualizado correctamente");
            jb_primero_actionPerformed();
        }

        private void jb_nuevo() {
            System.out.println("Action del boton jb_nuevo");
            model.setEmail(null);
            model.setNombre(null);
            view.jtf_email.setText(model.getEmail());
            view.jtf_nombre.setText(model.getNombre());
            
        }
}
