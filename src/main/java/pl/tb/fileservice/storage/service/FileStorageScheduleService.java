package pl.tb.fileservice.storage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.tb.fileservice.storage.config.enumerates.FileGroup;

@Service
@RequiredArgsConstructor
public class FileStorageScheduleService {

    private final  FileStorageService fileStorageService;

    @Scheduled(cron = "${storage.fileGroups.RECEIPT_EXCEL.readingScheduleCron}")
    public void loadNewReceiptExcelFiles() {

        fileStorageService.storeNewFiles(FileGroup.RECEIPT_EXCEL);
    }
}
