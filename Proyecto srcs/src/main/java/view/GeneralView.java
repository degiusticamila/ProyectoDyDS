package view;

import model.DataBase;
import model.SearchModel;
import presenter.SearchPresenter;

import javax.swing.*;
import java.awt.*;

public class GeneralView extends JFrame {
    public SearchView searchView;
    private JTabbedPane generalTabbedPane;
    //private JPanel generalContentPane;

    public GeneralView(SearchView searchView){
        //generalContentPane = new JPanel();
        generalTabbedPane = new JTabbedPane();

        //generalContentPane.add(generalTabbedPane);
        //add(searchView.getSearchPanel());
        add(generalTabbedPane);
        generalTabbedPane.add("Search in Wikipedia", searchView.getSearchPanel());

        setTitle("TV Series Info Repo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Define el tamaño de la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

    }
    public JTabbedPane getTabbedPane(){
        return generalTabbedPane;
    }

    public static void main(String[] args) {
        try {
            // Set System L&F
            UIManager.put("nimbusSelection", new Color(247,248,250));
            //UIManager.put("nimbusBase", new Color(51,98,140)); //This is redundant!

            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception e) {
            System.out.println("Something went wrong with UI!");
        }

        SwingUtilities.invokeLater(() -> {
            DataBase.loadDatabase();
            DataBase.saveInfo("test", "sarasa");

            SearchView searchView = new SearchView();
            GeneralView generalView = new GeneralView(searchView);

            SearchModel searchModel = new SearchModel();
            SearchPresenter sp = new SearchPresenter(searchView,searchModel);
            searchView.setSearchPresenter(sp);
            generalView.setVisible(true);
        });
    }

}
