package com.intek.kalabean.Classes;

import com.intek.kalabean.AddProduct.AddProductContract;
import com.intek.kalabean.AddProduct.AddProductPresenter;
import com.intek.kalabean.Brands.BrandsContract;
import com.intek.kalabean.Brands.BrandsPresenter;
import com.intek.kalabean.Chain_Store.ChainContract;
import com.intek.kalabean.Chain_Store.ChainPresenter;
import com.intek.kalabean.Complex.ComplexContract;
import com.intek.kalabean.Complex.ComplexPresenter;
import com.intek.kalabean.Data.KalaBeanDataSource;
import com.intek.kalabean.Data.KalaBeanRepository;
import com.intek.kalabean.Definition_Store.DefinitionContract;
import com.intek.kalabean.Definition_Store.DefinitionPresenter;
import com.intek.kalabean.Edit_User.EditUserContract;
import com.intek.kalabean.Edit_User.EditUserPresenter;
import com.intek.kalabean.Home.HomeContract;
import com.intek.kalabean.Home.HomePresenter;
import com.intek.kalabean.Login.LoginContract;
import com.intek.kalabean.Login.LoginPresenter;
import com.intek.kalabean.Login_With_User_Pass.LoginWithUserPassContract;
import com.intek.kalabean.Login_With_User_Pass.LoginWithUserPassPresenter;
import com.intek.kalabean.Markets.MarketsContract;
import com.intek.kalabean.Markets.MarketsPresenter;
import com.intek.kalabean.Model.ActivityKindList;
import com.intek.kalabean.Model.AddProduct;
import com.intek.kalabean.Model.BrandList;
import com.intek.kalabean.Model.ChainStoreList;
import com.intek.kalabean.Model.ComplexList;
import com.intek.kalabean.Model.FloorList;
import com.intek.kalabean.Model.LoggedinUser;
import com.intek.kalabean.Model.MallKindList;
import com.intek.kalabean.Model.Positions;
import com.intek.kalabean.Model.ProductList;
import com.intek.kalabean.Model.ShopCenterList;
import com.intek.kalabean.Model.ShopsList;
import com.intek.kalabean.Model.StoreDif;
import com.intek.kalabean.Model.StoreList;
import com.intek.kalabean.Model.Ticket;
import com.intek.kalabean.Model.User;
import com.intek.kalabean.Model.UserShop;
import com.intek.kalabean.Register.RegisterContract;
import com.intek.kalabean.Register.RegisterPresentr;
import com.intek.kalabean.Request_Product0.RequestContract;
import com.intek.kalabean.Request_Product0.RequestPresenter;
import com.intek.kalabean.Shops.ShopsContract;
import com.intek.kalabean.Shops.ShopsPresenter;
import com.intek.kalabean.ShowShop.ShowShopContract;
import com.intek.kalabean.ShowShop.ShowShopPresenter;
import com.intek.kalabean.ShowUserShop.UserShopContract;
import com.intek.kalabean.ShowUserShop.UserShopPresenter;
import com.intek.kalabean.Ticket.TicketContract;
import com.intek.kalabean.Ticket.TicketPresenter;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DatabaseMethods {
    public static KalaBeanDataSource kalaBeanDataSource;
    private static CompositeDisposable compositeDisposable = new CompositeDisposable();
    public String message = null;
    private static DefinitionContract.Presenter definitionPresenter = new DefinitionPresenter(new KalaBeanRepository());
    private static RequestContract.Presenter requestPresenter = new RequestPresenter(new KalaBeanRepository());
    private static AddProductContract.Presenter addProductPresenter = new AddProductPresenter(new KalaBeanRepository());
    private static BrandsContract.Presenter brandsPresenter = new BrandsPresenter(new KalaBeanRepository());
    private static ChainContract.Presenter chainPresenter = new ChainPresenter(new KalaBeanRepository());
    private static ComplexContract.Presenter complexPresenter = new ComplexPresenter(new KalaBeanRepository());
    private static HomeContract.Presenter homePresenter = new HomePresenter(new KalaBeanRepository());
    private static LoginContract.Presenter loginPresenter = new LoginPresenter(new KalaBeanRepository());
    private static MarketsContract.Presenter marketsPresenter = new MarketsPresenter(new KalaBeanRepository());
    private static RegisterContract.Presenter registerPresenter = new RegisterPresentr(new KalaBeanRepository());
    private static ShopsContract.Presenter shopsPresenter = new ShopsPresenter(new KalaBeanRepository());
    private static ShowShopContract.Presenter showShopPresenter = new ShowShopPresenter(new KalaBeanRepository());
    private static UserShopContract.Presenter userShopPresenter = new UserShopPresenter(new KalaBeanRepository());
    private static TicketContract.Presenter ticketPresenter = new TicketPresenter(new KalaBeanRepository());
    private static LoginWithUserPassContract.Presenter loginWithUserPassPresenter = new LoginWithUserPassPresenter(new KalaBeanRepository());
    private static EditUserContract.Presenter editUserPresenter = new EditUserPresenter(new KalaBeanRepository());
    private static int flag = 0;


    public static void getActivityKind(int id) {
        flag = id;
        DatabaseMethods.kalaBeanDataSource.getActivityKind().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ActivityKindList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        compositeDisposable.add(d);

                    }

                    @Override
                    public void onSuccess(ActivityKindList activityKindList) {
                        //activityKindListResult = activityKindList;
                        switch (flag) {
                            case 1:
                                definitionPresenter.onSuccess(activityKindList);
                                break;
                            case 2:
                                requestPresenter.onSuccess(activityKindList);
                                break;
                            case 3:
                                addProductPresenter.onSuccessActivityKind(activityKindList);
                                break;
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        switch (flag) {
                            case 1:
                                definitionPresenter.onError(e.toString());
                                break;
                            case 2:
                                requestPresenter.onError(e.toString());
                                break;
                            case 3:
                                addProductPresenter.onError(e.toString());
                        }

                    }
                });
    }

    public static void insertProduct(int id, AddProduct product) {
        flag = id;
        DatabaseMethods.kalaBeanDataSource.insertProduct(product).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<AddProduct>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(AddProduct product) {
                        switch (flag) {
                            case 3:
                                addProductPresenter.onSuccessInsertProduct(product);
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        switch (flag) {
                            case 3:
                                addProductPresenter.onError(e.toString());
                                break;
                        }
                    }
                });

    }

    public static void subCatId(int id) {
        flag = id;
        DatabaseMethods.kalaBeanDataSource.getPositions().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Positions>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Positions positions) {
                        switch (flag) {
                            case 3:
                                addProductPresenter.onSuccessSubCatId(positions);
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        switch (flag) {
                            case 3:
                                addProductPresenter.onError(e.toString());
                                break;
                        }
                    }
                });
    }

    public static void getBrands(int id,int sellCenterCatId){
        flag = id;
        DatabaseMethods.kalaBeanDataSource.getBrands(sellCenterCatId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<BrandList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(BrandList brandList) {
                        switch (flag){
                            case 4:
                                brandsPresenter.onSuccessGetBrand(brandList);
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        switch (flag){
                            case 4:
                                brandsPresenter.onError(e.toString());
                                break;
                        }
                    }
                });
    }

    public static void getChainStore(int id,int sellCenterCatId,int cityId){
        flag = id;
        DatabaseMethods.kalaBeanDataSource.getChainStore(sellCenterCatId,cityId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ChainStoreList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ChainStoreList chainStoreList) {
                        switch (flag){
                            case 5:
                                chainPresenter.onSuccessGetChainStore(chainStoreList);
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        switch (flag){
                            case 5:
                                chainPresenter.onError(e.toString());
                                break;
                        }
                    }
                });
    }

    public static void getComplex(int id,int sellCenterId,int cityId){
        flag = id;
        DatabaseMethods.kalaBeanDataSource.getComplex(sellCenterId,cityId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ComplexList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ComplexList complexList) {
                        switch (flag){
                            case 6:
                                complexPresenter.onSuccessGetComplex(complexList);
                                break;
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        switch (flag){
                            case 6:
                                complexPresenter.onError(e.toString());
                                break;
                        }
                    }
                });
    }

    public static void getStoreKind(int id){
        flag = id;
        DatabaseMethods.kalaBeanDataSource.getStoreKind().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MallKindList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(MallKindList mallKindList) {
                        switch (flag){
                            case 1:
                                definitionPresenter.onSuccessStoreKind(mallKindList);
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        switch (flag){
                            case 1:
                                definitionPresenter.onError(e.toString());
                                break;
                        }
                    }
                });
    }

    public static void getShopCenterList(int id,int idCenterCat){
        flag = id;
        DatabaseMethods.kalaBeanDataSource.getShopCenterList(idCenterCat).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ShopCenterList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ShopCenterList shopCenterList) {
                        switch (flag){
                            case 1:
                                definitionPresenter.onSuccessShopCenterList(shopCenterList);
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        switch (flag){
                            case 1:
                                definitionPresenter.onError(e.toString());
                                break;
                        }
                    }
                });
    }

    public static void getFloorList(int id,int idCenter){
        flag = id;
        DatabaseMethods.kalaBeanDataSource.getFloorList(idCenter).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<FloorList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(FloorList floorList) {
                        switch (flag){
                            case 1:
                                definitionPresenter.onSuccessFloorList(floorList);
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        switch (flag){
                            case 1:
                                definitionPresenter.onError(e.toString());
                                break;
                        }
                    }
                });
    }

    public static void storeDefinition(int id, StoreDif storeDif){
        flag = id;
        DatabaseMethods.kalaBeanDataSource.storeDefinition(storeDif).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<StoreDif>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(StoreDif storeDif) {
                        definitionPresenter.onSuccessStoreDefinition(storeDif);
                    }

                    @Override
                    public void onError(Throwable e) {
                        definitionPresenter.onError(e.toString());
                    }
                });

    }

    public static void getProductList(int id,int shopId){
        flag = id;
        DatabaseMethods.kalaBeanDataSource.getProduct(shopId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ProductList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ProductList productList) {
                        switch (flag){
                            case 7:
                                homePresenter.onSuccessGetProductList(productList);
                                break;
                            case 12:
                                showShopPresenter.onSuccessGetProduct(productList);
                                break;
                            case 13:
                                userShopPresenter.onSuccessGetProduct(productList);
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        switch (flag){
                            case 7:
                                homePresenter.onError(e.toString());
                                break;
                            case 12:
                                showShopPresenter.onError(e.toString());
                                break;
                            case 13:
                                userShopPresenter.onError(e.toString());
                                break;
                        }
                    }
                });
    }

    public static void getUserShop(int id,int creatorId){

        flag = id;
        DatabaseMethods.kalaBeanDataSource.getUserShop(creatorId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserShop>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(UserShop userShop) {
                        switch (flag){
                            case 13:
                                userShopPresenter.onSuccessUserShop(userShop);
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        switch (flag){
                            case 13:
                                userShopPresenter.onError(e.toString());
                                break;
                        }
                    }
                });

    }

    public static void sendTicket(int id,Ticket ticket){
        flag = id;
        DatabaseMethods.kalaBeanDataSource.sendTicket(ticket).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Ticket>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Ticket ticket) {
                        switch (flag){
                            case 14:
                                ticketPresenter.onSuccessSendTicket(ticket);
                                break;
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        switch (flag){
                            case 14:
                                ticketPresenter.onError(e.toString());
                                break;
                        }
                    }
                });
    }

    public static void getReductedProductList(int id,int shopId){
        flag = id;
        DatabaseMethods.kalaBeanDataSource.getProduct(shopId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ProductList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ProductList productList) {
                        switch (flag){
                            case 7:
                                homePresenter.onSuccessGetReductedProductList(productList);
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        switch (flag){
                            case 7:
                                homePresenter.onError(e.toString());
                                break;
                        }
                    }
                });
    }

    public static void getShopList(int id,int sellCenterId,int floorId){
        flag = id;
        DatabaseMethods.kalaBeanDataSource.getShops(sellCenterId,floorId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ShopsList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ShopsList shopsList) {
                        switch (flag){
                            case 7:
                                homePresenter.onSuccessGetShopList(shopsList);
                                break;
                            case 11:
                                shopsPresenter.onSuccessGetShops(shopsList);
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        switch (flag){
                            case 7:
                                homePresenter.onError(e.toString());
                                break;
                            case 11:
                                shopsPresenter.onError(e.toString());
                        }
                    }
                });
    }

    public static void login(int id,User user){
        flag = id;
        DatabaseMethods.kalaBeanDataSource.login(user).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<LoggedinUser>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(LoggedinUser loggedinUser) {
                        switch (flag){
                            case 9:
                                loginWithUserPassPresenter.onSuccessLogin(loggedinUser);
                                break;
                            case 10:
                                registerPresenter.onSuccessLogin(loggedinUser);
                                break;
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        switch (flag){
                            case 9:
                                loginWithUserPassPresenter.onError(e.toString());
                                break;
                            case 10:
                                registerPresenter.onError(e.toString());
                                break;
                        }

                    }
                });
    }

    public static void getMarkets(int id,int sellCenterCatId,int cityCenterId){
        flag = id;
        DatabaseMethods.kalaBeanDataSource.getMarkets(sellCenterCatId,cityCenterId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<StoreList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(StoreList storeList) {
                        switch (flag){
                            case 8:
                                marketsPresenter.onSuccessGetMarket(storeList);
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        switch (flag){
                            case 8:
                                marketsPresenter.onError(e.toString());
                                break;
                        }
                    }
                });
    }

    public static void register(User user){
        DatabaseMethods.kalaBeanDataSource.register(user).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(User user) {
                        registerPresenter.onSuccessRegister(user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        registerPresenter.onError(e.toString());
                    }
                });
    }

    public static void getUserInfo(int userId){
        DatabaseMethods.kalaBeanDataSource.getUserInfo(userId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(User user) {
                        editUserPresenter.onSuccessGetUserInfo(user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        editUserPresenter.onError(e.toString());
                    }
                });
    }
    public static void editUser(int uid,String mobile,String email){
        DatabaseMethods.kalaBeanDataSource.editUser(uid, mobile, email).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(User user) {
                        editUserPresenter.onSuccessEditUser(user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        editUserPresenter.onError(e.toString());
                    }
                });
    }


}
