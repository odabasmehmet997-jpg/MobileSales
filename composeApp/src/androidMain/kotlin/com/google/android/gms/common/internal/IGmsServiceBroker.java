package com.google.android.gms.common.internal;

import android.os.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public interface IGmsServiceBroker extends IInterface {

    /* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
    abstract class Stub extends Binder implements IGmsServiceBroker {
        protected Stub() {
            attachInterface(this, "com.google.android.gms.common.internal.IGmsServiceBroker");
        }

        @NonNull
        @KeepForSdk
        @CanIgnoreReturnValue
        public IBinder asBinder() {
            return this;
        }

        public final boolean onTransact(int i2, @NonNull Parcel parcel, @Nullable Parcel parcel2, int i3) throws RemoteException {
            IGmsCallbacks iGmsCallbacks;
            if (16777215 < i2) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            IBinder readStrongBinder = parcel.readStrongBinder();
            GetServiceRequest getServiceRequest = null;
            if (null == readStrongBinder) {
                iGmsCallbacks = null;
            } else {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsCallbacks");
                iGmsCallbacks = queryLocalInterface instanceof IGmsCallbacks ? (IGmsCallbacks) queryLocalInterface : new zzab(readStrongBinder);
            }
            if (46 == i2) {
                if (0 != parcel.readInt()) {
                    getServiceRequest = GetServiceRequest.CREATOR.createFromParcel(parcel);
                }
                getService(iGmsCallbacks, getServiceRequest);
                Preconditions.checkNotNull(parcel2);
                parcel2.writeNoException();
                return true;
            } else if (47 == i2) {
                if (0 != parcel.readInt()) {
                    zzak createFromParcel = zzak.CREATOR.createFromParcel(parcel);
                }
                throw new UnsupportedOperationException();
            } else {
                parcel.readInt();
                if (4 != i2) {
                    parcel.readString();
                    if (1 != i2) {
                        if (!(2 == i2 || 23 == i2 || 25 == i2 || 27 == i2)) {
                            if (30 != i2) {
                                if (34 != i2) {
                                    if (!(41 == i2 || 43 == i2 || 37 == i2 || 38 == i2)) {
                                        switch (i2) {
                                            case 5:
                                            case 6:
                                            case 7:
                                            case 8:
                                            case 11:
                                            case 12:
                                            case 13:
                                            case 14:
                                            case 15:
                                            case 16:
                                            case 17:
                                            case 18:
                                                break;
                                            case 9:
                                                parcel.readString();
                                                parcel.createStringArray();
                                                parcel.readString();
                                                parcel.readStrongBinder();
                                                parcel.readString();
                                                if (0 != parcel.readInt()) {
                                                    Bundle bundle = Bundle.CREATOR.createFromParcel(parcel);
                                                    break;
                                                }
                                                break;
                                            case 10:
                                                parcel.readString();
                                                parcel.createStringArray();
                                                break;
                                            case 19:
                                                parcel.readStrongBinder();
                                                if (0 != parcel.readInt()) {
                                                    Bundle bundle2 = Bundle.CREATOR.createFromParcel(parcel);
                                                    break;
                                                }
                                                break;
                                            case 20:
                                                break;
                                        }
                                    }
                                } else {
                                    parcel.readString();
                                }
                            }
                            parcel.createStringArray();
                            parcel.readString();
                            if (0 != parcel.readInt()) {
                                Bundle bundle3 = Bundle.CREATOR.createFromParcel(parcel);
                            }
                        }
                        if (0 != parcel.readInt()) {
                            Bundle bundle4 = Bundle.CREATOR.createFromParcel(parcel);
                        }
                    } else {
                        parcel.readString();
                        parcel.createStringArray();
                        parcel.readString();
                        if (0 != parcel.readInt()) {
                            Bundle bundle5 = Bundle.CREATOR.createFromParcel(parcel);
                        }
                    }
                }
                throw new UnsupportedOperationException();
            }
        }
    }

    @KeepForSdk
    void getService(@NonNull IGmsCallbacks iGmsCallbacks, @Nullable GetServiceRequest getServiceRequest) throws RemoteException;
}
