package method_three;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cache {

	private Map<Integer, HashMap<Integer, Memory>> cache_memory_set;

	private HashMap<Integer, Memory> cache_memory_arr_set;

	private Memory[] memory;

	public Cache() {
		this.memory = new Memory[2048];
		for (int i = 0; i < 256; i++) {
			for (int j = 0; j < 8; j++) {
				this.memory[i * 8 + j] = new Memory(i, j);
			}
		}

		this.cache_memory_set = new HashMap<Integer, HashMap<Integer, Memory>>();

		for (int i = 0; i < 8; i++) {
			this.cache_memory_arr_set = new HashMap<Integer, Memory>();
			for (int j = 0; j < 2; j++) {
				int x = (int) (Math.random() * 255);// 随机生成主存的组内序号
				this.cache_memory_arr_set.put(j, this.memory[x * 8 + i]);
			}
			this.cache_memory_set.put(i, this.cache_memory_arr_set);
		}
	}

	public void Entry_set_cache() {
		Iterator iterator = this.cache_memory_set.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<Integer, HashMap<Integer, Memory>> entry = (Map.Entry<Integer, HashMap<Integer, Memory>>) iterator
					.next();

			HashMap<Integer, Memory> arr_map = entry.getValue();

			System.out.println("cache的组号" + entry.getKey() + "");
			Iterator iterator_arr = arr_map.entrySet().iterator();

			while (iterator_arr.hasNext()) {
				Map.Entry<Integer, Memory> arr_entry = (Map.Entry<Integer, Memory>) iterator_arr
						.next();
				System.out.println("cache组内序号:" + arr_entry.getKey());
				System.out.println("主存的组号:"
						+ arr_entry.getValue().get_Memory_num());
				System.out.println("主存的组内序号:"
						+ arr_entry.getValue().get_Memory_arr_num());
			}
			System.out.println("----------");
		}
	}

	public void Find_In_Cache_Set(Memory memory) {
		/**
		 * @params 先是在主存组内序号进行查找 然后再比较主存的组号,未命中的话,默认放在cache的第一组
		 * */
		int cache_arr_num = memory.get_Memory_arr_num();
		HashMap<Integer, Memory> arr_map = this.cache_memory_set
				.get(cache_arr_num);

		Iterator arr_iterator = arr_map.entrySet().iterator();
		while (true) {

			if (!arr_iterator.hasNext()) {
				System.out.println("no target!!");
				arr_map.put(0, memory);
				break;
			} else {
				Map.Entry<Integer, Memory> arr_entry = (Map.Entry<Integer, Memory>) arr_iterator
						.next();
				if (memory.get_Memory_num() == arr_entry.getValue()
						.get_Memory_num()) {
					System.out.println("target!!");
					break;
				}
			}

		}
	}

	public static void main(String[] args) {
		Cache test = new Cache();
		test.Entry_set_cache();
		Memory test_memory = new Memory(0, 5);
		test.Find_In_Cache_Set(test_memory);
		test.Find_In_Cache_Set(test_memory);

	}

}