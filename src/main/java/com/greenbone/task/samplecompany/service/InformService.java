package com.greenbone.task.samplecompany.service;

import com.greenbone.task.samplecompany.domain.Computer;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public interface InformService {

    boolean informAdmin(Computer computer, int count);

    boolean checkComputersAllottedExceeded(Computer computer);
}
