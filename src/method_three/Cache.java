package method_three;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cache {
     
	  /*
	   * ������ӳ��,
	   * cacheΪ2·����,��8����
	   * ������256���飬ÿ��8��,��2048��
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
		  * cache��һ��Map��Ϊ��Ҫ�����ݽṹ,��Map����д�ڶ�����ʽҲ��һ��Map,��Ϊcacheÿ����ı�ʾ
		  * */
		for (int cacheArrNum = 0; cacheArrNum < 8; cacheArrNum++) {
			this.cache_memory_arr_set = new HashMap<Integer, Memory>();
			for (int cacheArrInnerNum = 0; cacheArrInnerNum < 2; cacheArrInnerNum++) {
				int memoryTagRandom = (int) (Math.random() * 255);// ���������������
				this.cache_memory_arr_set.put(cacheArrInnerNum, this.memory[memoryTagRandom * 8 + cacheArrNum]);
			}
			this.cache_memory_set.put(cacheArrNum, this.cache_memory_arr_set);
		}
	}
   
	/*
	 **�鿴CACHE���ڴ澵��
	 * */
	public void observeeEntrysetIncache() 
	{
		Iterator iterator = this.cache_memory_set.entrySet().iterator();

		while (iterator.hasNext()) 
		{
			Map.Entry<Integer, HashMap<Integer, Memory>> entry = (Map.Entry<Integer, HashMap<Integer, Memory>>) iterator
					.next();

			HashMap<Integer, Memory> arr_map = entry.getValue();

			System.out.println("cache�����" + entry.getKey() + "");
			Iterator iterator_arr = arr_map.entrySet().iterator();

			while (iterator_arr.hasNext()) {
				Map.Entry<Integer, Memory> arr_entry = (Map.Entry<Integer, Memory>) iterator_arr
						.next();
				System.out.println("cache�������:" + arr_entry.getKey());
				System.out.println("��������:"
						+ arr_entry.getValue().get_Memory_num());
				System.out.println("������������:"
						+ arr_entry.getValue().get_Memory_arr_num());
			}
			System.out.println("----------");
		}
	}

	public void findInCacheSet(Memory memory) {
		/**
		 * @author ����������������Ž��в��� Ȼ���ٱȽ���������,δ���еĻ�,Ĭ�Ϸ���cache�ĵ�һ��
		 * */
		int cache_arr_num = memory.get_Memory_arr_num();
		HashMap<Integer, Memory> arr_map = this.cache_memory_set.get(cache_arr_num);

		Iterator arr_iterator = arr_map.entrySet().iterator();
		while (true) {
         /*
          *������cache������˳�����  
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