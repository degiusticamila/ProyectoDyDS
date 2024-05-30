package presenter;

import model.DataBaseModel;
import view.SearchView;

public class SavePresenter {
    private SearchView searchView;
    private DataBaseModel dataBaseModel;

    public void onEventSaveLocallyButton(){
        dataBaseModel.saveLocally();
    }
}
