package presenter;

import model.DataBaseModel;
import model.ModelListener;
import view.SearchView;
import view.StorageView;

public class SavePresenter {
    private SearchView searchView;
    private StorageView storageView;
    private DataBaseModel dataBaseModel;
    private PagePresenter pagePresenter;

    public SavePresenter(SearchView searchView,StorageView storageView,DataBaseModel dataBaseModel){
        this.searchView = searchView;
        this.storageView = storageView;
        this.dataBaseModel = dataBaseModel;
    }
    public void onEventSaveLocallyButton(){
        dataBaseModel.addListener(new ModelListener() {
            @Override
            public void hasFinished() {
                showText();
            }
        });
        dataBaseModel.saveLocally(pagePresenter.getLastSelectedResultTitle(), pagePresenter.getLastText());
        System.out.println("guardado :)");
    }
    private void showText(){
        //actualiza el desplegable de la storage view.
        System.out.println("Falta actualizar Storage View, pero funciona!");
    }
    public void setPagePresenter(PagePresenter pagePresenter){
        this.pagePresenter = pagePresenter;
    }
}
