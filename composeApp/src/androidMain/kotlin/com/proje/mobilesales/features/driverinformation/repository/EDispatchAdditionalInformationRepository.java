package com.proje.mobilesales.features.driverinformation.repository;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.customer.model.Customer;
import com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Callable;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.springframework.http.HttpHeaders;


public final class EDispatchAdditionalInformationRepository extends BaseRepository implements IEDispatchAdditionalInformationRepository {
    private final String tag = "EDispatchAdditionalInformationRepository";

    public EDispatchAdditionalInformationRepository() {
        super(baseRepositorybaseErp2);
    }
    public void getEDispatchAdditionalInfo(final Customer customer, ResponseListener<EDispatchAdditionalInfo> responseListener) {
        Intrinsics.checkNotNullParameter(customer, "customer");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        EDispatchAdditionalInfo eDispatchAdditionalInfo = new EDispatchAdditionalInfo();
        eDispatchAdditionalInfo.carrierTaxNr = customer.getTaxNr();
        eDispatchAdditionalInfo.carrierName = customer.getTitle();
        eDispatchAdditionalInfo.carrierCounty = customer.getTown();
        eDispatchAdditionalInfo.carrierCity = customer.getCity();
        eDispatchAdditionalInfo.carrierPostCode = customer.getPostCode();
        if (TextUtils.isEmpty(customer.getCountryCode())) {
            responseListener.onResponse(eDispatchAdditionalInfo);
            return;
        }
        String str = "SELECT COUNTRYNAME FROM COUNTRY WHERE COUNTRYCODE = '" + customer.getCountryCode() + '\'';
        final Observable defer = Observable.defer(new Callable() { // from class: com.proje.mobilesales.features.driverinformation.repository.EDispatchAdditionalInformationRepositoryExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public Object call() {
                final ObservableSource eDispatchAdditionalInfolambda0;
                eDispatchAdditionalInfolambda0 = getEDispatchAdditionalInfolambda0(EDispatchAdditionalInformationRepository.this, str);
                return eDispatchAdditionalInfolambda0;
            }
        });
        Intrinsics.checkNotNullExpressionValue(defer, "defer(...)");
        final Observable observeOn = defer.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Function1<Throwable, Unit> function1 = new Function1<Throwable, Unit>() {
            public  Unit invoke(final Object th) {
                this.invoke2(th);
                return Unit.INSTANCE;
            }
            public void invoke2(final Throwable th) {
                final String str2;
                str2 = tag;
                Log.e(str2, "getEDispatchAdditionalInfo:", th);
            }
        };
        final Observable doOnError = observeOn.doOnError(new Consumer() {
            public void accept(final Object obj) {
                getEDispatchAdditionalInfolambda1(Function1.this, obj);
            }
        });
        Function1<Cursor, Unit> function12 = new Function1<Cursor, Unit>() {

            public Unit invoke(final Object cursor) {
                this.invoke2((Cursor) cursor);
                return Unit.INSTANCE;
            }
            public void invoke2(final Cursor cursor) {
                final String str2;
                try {
                    if (null != cursor) {
                        try {
                            if (0 < cursor.getCount() && cursor.moveToFirst()) {
                                do {
                                    EDispatchAdditionalInfo.this.carrierCountry = cursor.getString(cursor.getColumnIndex("COUNTRYNAME"));
                                } while (cursor.moveToNext());
                            }
                            if (!cursor.isClosed()) {
                                cursor.close();
                            }
                        } catch (final Exception e2) {
                            str2 = this.tag;
                            Log.e(str2, "error:", e2);
                            cursor.close();
                            responseListener.onResponse(EDispatchAdditionalInfo.this);
                        }
                    }
                } catch (final Throwable th) {
                    cursor.close();
                    throw th;
                }
            }
        };
        final Consumer consumer = new Consumer() {
            public void accept(final Object obj) {
                getEDispatchAdditionalInfolambda2(Function1.this, obj);
            }
        };
        Function1<Throwable, Unit> function13 = new Function1<Throwable, Unit>() {

            public  Unit invoke(final Object th) {
                this.invoke2(th);
                return Unit.INSTANCE;
            }

            public void invoke2(final Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                responseListener.onError(throwable.getMessage());
            }
        };
        doOnError.subscribe(consumer, new Consumer() {
            public void accept(final Object obj) {
                getEDispatchAdditionalInfolambda3(Function1.this, obj);
            }
        });
    }
    public static ObservableSource getEDispatchAdditionalInfolambda0(final EDispatchAdditionalInformationRepository this0, final String sql) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(sql, "sql");
        return Observable.just(this0.getLogoSqlBriteDatabase().query(sql));
    }

    public static void getEDispatchAdditionalInfolambda1(final Function1 tmp0, final Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }
    public static void getEDispatchAdditionalInfolambda2(final Function1 tmp0, final Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }
    public static void getEDispatchAdditionalInfolambda3(final Function1 tmp0, final Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "tmp0");
        tmp0.invoke(obj);
    }
}
