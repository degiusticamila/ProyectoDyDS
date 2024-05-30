package presenter;

import model.DataBaseModel;
import model.ModelListener;
import view.SearchView;
import view.StorageView;

public class SavePresenter {
    private SearchView searchView;
    private StorageView storageView;
    private DataBaseModel dataBaseModel;
    public SavePresenter(SearchView searchView,StorageView storageView,DataBaseModel dataBaseModel){
        this.searchView = searchView;
        this.storageView = storageView;
        this.dataBaseModel = dataBaseModel;
    }
    public void onEventSaveLocallyButton(){
        dataBaseModel.addListener(new ModelListener() {
            @Override
            public void searchFinished() {
                showText();
            }
        });
        dataBaseModel.saveLocally();
        //update storage view?
    }
    private void showText(){

    }
}
