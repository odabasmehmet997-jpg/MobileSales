package com.proje.mobilesales.features.tools.repository;

import com.proje.mobilesales.core.base.IBaseRepository;
import com.proje.mobilesales.features.tools.model.database.StartInfo;

public interface IStartInfoEnterRepository extends IBaseRepository {
    void sendStartInfoEnter(StartInfo startInfo);
}
