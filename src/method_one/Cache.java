package method_one;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Cache {/*
                     *ֱ������ӳ���Cache����,��ʼ������Ϊ2048����,cache��16��
                     */

	       private Map<Integer,Memory> cache_memory_set;
	       //cache_memoryӳ���ϵ����
	       
	       private Memory[] memory;//ģ��һ������
	       
	       public Cache()
	       {
	    	   /*
	    	    *��ʼ������
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
	        * ��ʼ��,���װ��
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
	    	     * ͨ����ϣ����ң�Ȼ��ֻ��Ҫһ���ԱȽ�����,�Ϳ����ж�CPU�Ƿ�����
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
