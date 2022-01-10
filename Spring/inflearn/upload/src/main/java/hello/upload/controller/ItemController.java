package hello.upload.controller;

import hello.upload.domain.Item;
import hello.upload.domain.ItemRepository;
import hello.upload.domain.UploadFile;
import hello.upload.file.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final FileStore fileStore;

    @GetMapping("/items/new")
    public String newItem(@ModelAttribute ItemForm form) {
        return "item-form";
    }

    @PostMapping("/items/new")
    public String saveItem(@ModelAttribute ItemForm form, RedirectAttributes redirectAttributes) throws IOException {
        //MultipartFile attachFile = form.getAttachFile();
        //UploadFile uploadFile = fileStore.storeFile(attachFile);
        UploadFile attachFile = fileStore.storeFile(form.getAttachFile());

        //List<MultipartFile> imageFiles = form.getImageFiles();
        //List<UploadFile> uploadFiles = fileStore.storeFiles(imageFiles);
        List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());

        /**
         * 데이터베이스에 저장
         *
         * 중요!!
         *      -> 파일은 DB 에 저장하는 것이 아님. (스토리지에 저장 / AWS 의 경우 S3 저장)
         *      -> DB 에는 파일에 저장된 상대적인 경로만 저장.
         */
        Item item = new Item();

        item.setItemName(form.getItemName());
        item.setAttachFile(attachFile);
        item.setImageFiles(storeImageFiles);

        itemRepository.save(item);

        redirectAttributes.addAttribute("itemId", item.getId());

        return "redirect:/items/{itemId}";
    }

    @GetMapping("/items/{id}")
    public String items(@PathVariable Long id, Model model) {
        Item item = itemRepository.findById(id);
        model.addAttribute("item", item);

        return "item-view";
    }
}
