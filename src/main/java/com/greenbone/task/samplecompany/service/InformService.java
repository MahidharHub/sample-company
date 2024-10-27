package com.greenbone.task.samplecompany.service;

import com.greenbone.task.samplecompany.domain.Computer;

public interface InformService {

    boolean informAdmin(Computer computer, int count);

    boolean checkComputersAllottedExceeded(Computer computer);
}
