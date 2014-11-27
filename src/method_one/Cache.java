package method_one;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Cache {/*
                     *直接相联映射的Cache仿真,初始化主存为2048个块,cache有16组
                     */

	       private Map<Integer,Memory> cache_memory_set;
	       //cache_memory映射关系集合
	       
	       private Memory[] memory;//模拟一块主存
	       
	       public Cache()
	       {
	    	   /*
	    	    *初始化主存
	    	    * */
	    	   this.memory=new Memory[2048];
	    	   this.cache_memory_set=new HashMap<Integer,Memory>();
	    	   for(int memorNum=0; memorNum<128;memorNum++)
	    	   {
	    		   for(int memoryArrNumber=0;memoryArrNumber<16;memoryArrNumber++)
	    		   {
	    			   this.memory[memorNum*16+memoryArrNumber]=new Memory(memorNum,memoryArrNumber);
	    		   }
	    	   }
	    	   
	    	   this.random_put_in_cache();
	    	  
	       }
	       /*
	        * 初始化,随机装载
	        * */
	       private void random_put_in_cache()
	       {
	    	      for(int cacheArrNum=0;cacheArrNum<16;cacheArrNum++)
	    	      {
	    	    	  int radomNumber=(int)(Math.random()*16);
	    	    	  this.cache_memory_set.put(cacheArrNum, this.memory[cacheArrNum+radomNumber*16]);
	    	      }
	       }
	       private boolean find_memory_in_cache(Memory target_memory)
	       {
	    	    /*
	    	     * 通过哈希表查找，然后只需要一次性比较主存,就可以判定CPU是否命中
	    	     * */
	    	   int arr_num=target_memory.get_Memory_arr_num();
	    	   Memory cache_memory=this.cache_memory_set.get(arr_num);
	    	   if(cache_memory.get_Memory_num()==target_memory.get_Memory_num())
	    	   {
	    		   System.out.println("the CPU is target!!!");
	    		   return true;
	    	   }else{
	    		   System.out.println("the CPU is not target!!!");
	    		   this.cache_memory_set.put(arr_num, target_memory);   
	    		   return false;
	    	   }
	       }   
	       public static void main(String [] args)
	       {
                 Cache  cache=new Cache();
                 Memory test=new Memory(8,13);
                 
                cache.find_memory_in_cache(test);
                cache.find_memory_in_cache(test);

	       }

}
