package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {

    /**
     * 서로 다른 고객이 같은 파일이름을 업로드 하는 경우 기존 파일 이름과 충돌이 날 수 있다.
     * 따라서 서버에서는 저장할 파일명이 겹치지 않도록 내부에서 관리하는 별도의 파일명이 필요.
     */
    private String uploadFileName;  // 고객이 업로드한 파일명
    private String storeFileName;   // 서버 내부에서 관리하는 파일명

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
