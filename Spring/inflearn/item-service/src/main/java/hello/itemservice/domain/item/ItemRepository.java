package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    /**
     * 여러 쓰레드가 동시에 store에 접근할 경우 HashMap 사용하면 안됨
     *      -> ConcurrentHashMap 사용
     */
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    // 아이템 저장
    public Item save(Item item) {
        item.setId((++sequence));
        store.put(item.getId(), item);
        return item;
    }

    // 아이템 조회
    public Item findById(Long id) {
        return store.get(id);
    }

    // 아이템 목록 조회
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    // 아이템 수정
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);

        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
