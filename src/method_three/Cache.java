package method_three;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cache {
     
	  /*
	   * 组相联映射,
	   * cache为2路组联,分8个组
	   * 主存是256个组，每组8块,共2048块
	   * */
	private Map<Integer, HashMap<Integer, Memory>> cache_memory_set;
    
	private HashMap<Integer, Memory> cache_memory_arr_set;

	private Memory[] memory;

	public Cache() {
		this.memory = new Memory[2048];
		for (int memoryNum = 0; memoryNum< 256; memoryNum++) {
			for (int memoryArrInnerNum = 0; memoryArrInnerNum< 8; memoryArrInnerNum++) {
				this.memory[memoryNum * 8 + memoryArrInnerNum] = new Memory(memoryNum, memoryArrInnerNum);
			}
		}

		this.cache_memory_set = new HashMap<Integer, HashMap<Integer, Memory>>();

		 /*
		  * cache用一个Map来为主要的数据结构,此Map中填写第二个范式也是一个Map,作为cache每个组的表示
		  * */
		for (int cacheArrNum = 0; cacheArrNum < 8; cacheArrNum++) {
			this.cache_memory_arr_set = new HashMap<Integer, Memory>();
			for (int cacheArrInnerNum = 0; cacheArrInnerNum < 2; cacheArrInnerNum++) {
				int memoryTagRandom = (int) (Math.random() * 255);// 随机生成主存的序号
				this.cache_memory_arr_set.put(cacheArrInnerNum, this.memory[memoryTagRandom * 8 + cacheArrNum]);
			}
			this.cache_memory_set.put(cacheArrNum, this.cache_memory_arr_set);
		}
	}
   
	/*
	 **查看CACHE的内存镜像
	 * */
	public void observeeEntrysetIncache() 
	{
		Iterator iterator = this.cache_memory_set.entrySet().iterator();

		while (iterator.hasNext()) 
		{
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

	public void findInCacheSet(Memory memory) {
		/**
		 * @author 先是在主存组内序号进行查找 然后再比较主存的组号,未命中的话,默认放在cache的第一组
		 * */
		int cache_arr_num = memory.get_Memory_arr_num();
		HashMap<Integer, Memory> arr_map = this.cache_memory_set.get(cache_arr_num);

		Iterator arr_iterator = arr_map.entrySet().iterator();
		while (true) {
         /*
          *组内是cache组内是顺序查找  
          * */
			if (!arr_iterator.hasNext()) 
			{
				System.out.println("no target!!");
				arr_map.put(0, memory);
				break;
			} 
			else 
			{
				Map.Entry<Integer, Memory> arr_entry = (Map.Entry<Integer, Memory>) arr_iterator.next();
				if (memory.get_Memory_num() == arr_entry.getValue().get_Memory_num()) 
				{
					System.out.println("target!!");
					break;
				}
			}

		}
	}

	public static void main(String[] args) {
		Cache test = new Cache();
		test.observeeEntrysetIncache();
		Memory test_memory = new Memory(0, 5);
		test.findInCacheSet(test_memory);
		test.findInCacheSet(test_memory);

	}

}