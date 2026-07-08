package com.proje.mobilesales.features.reports.repository;

import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsActivity;
import kotlin.jvm.internal.Intrinsics;
import static com.proje.mobilesales.features.gpsinfo.repository.GpsInfoRepository.baseRepositorybaseErp2;
public final class ItemReportsRepository extends BaseRepository implements IItemReportsRepository {
    public ItemReportsRepository() {
        super(baseRepositorybaseErp2);
    }
    public void getReportOnline(final ResponseListener<?> responseLister) {
        Intrinsics.checkNotNullParameter(responseLister, "responseLister");
        this.getBaseErp().getReportOnline((ResponseListener<UserReportsActivity>) responseLister);
    }
}
