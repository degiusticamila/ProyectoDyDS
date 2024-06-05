package view;

import model.*;
import presenter.*;
import utils.Utilities;

import javax.swing.*;
import java.util.Date;


public class Window extends JFrame {
    public SearchView searchView;
    public StorageView storageView;
    public ScoreView scoreView;
    private JTabbedPane generalTabbedPane;
    //private JPanel generalContentPane;

    public Window(SearchView searchView, StorageView storageView, ScoreView scoreView){

        createGeneralTabbedPane();
        generalTabbedPane.add("Search in Wikipedia", searchView.getSearchPanel());
        generalTabbedPane.add("Stored Info", storageView.getStoragePanel());
        generalTabbedPane.add("Series Score",scoreView.getScorePanel());

        setTitle("TV Series Info Repo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Define el tamaño de la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

    }
    public void createGeneralTabbedPane(){
        generalTabbedPane = new JTabbedPane();
        add(generalTabbedPane);
    }
    public JTabbedPane getTabbedPane(){
        return generalTabbedPane;
    }

    public static void main(String[] args) {
        Utilities.setNimbusTheme();
        SwingUtilities.invokeLater(() -> {
            DataBase.loadDatabase();

            SearchView searchView = new SearchView();
            StorageView storageView = new StorageView();
            ScoreView scoreView = new ScoreView();
            Window generalView = new Window(searchView,storageView,scoreView);

            PageModel pageModel = new PageModel();
            SearchModel searchModel = new SearchModel();
            SaveModel dataBaseModel = new SaveModel();
            GetDBModel dbModel = new GetDBModel();
            DeleteDBModel deleteDBModel = new DeleteDBModel();
            ScoreModel scoreModel = new ScoreModel();

            SavePresenter savePresenter = new SavePresenter(searchView,storageView,dataBaseModel);
            PagePresenter pagePresenter = new PagePresenter(searchView,pageModel);
            SearchPresenter searchPresenter = new SearchPresenter(searchView,searchModel);
            StoragePresenter storagePresenter = new StoragePresenter(storageView,dataBaseModel,dbModel,deleteDBModel);
            ScorePresenter scorePresenter = new ScorePresenter(searchView,scoreView,scoreModel);

            savePresenter.setPagePresenter(pagePresenter);

            storageView.setStoragePresenter(storagePresenter);
            scorePresenter.setPagePresenter(pagePresenter);
            searchPresenter.setScorePresenter(scorePresenter);

            searchView.setSearchPresenter(searchPresenter);
            searchView.setPagePresenter(pagePresenter);
            searchView.setSavePresenter(savePresenter);
            searchView.setScorePresenter(scorePresenter);
            scoreView.setScorePresenter(scorePresenter);


            generalView.setVisible(true);
        });
    }
}
