package method_one;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Cache {//直接相联映射

	       private Map<Integer,Memory> cache_memory_set;//映射关系集合
	       
	       private Memory[] memory;
	       
	       public Cache()
	       {
	    	   this.memory=new Memory[2048];
	    	   this.cache_memory_set=new HashMap<Integer,Memory>();
	    	   for(int i=0;i<128;i++)
	    	   {
	    		   for(int j=0;j<16;j++)
	    		   {
	    			   this.memory[i*16+j]=new Memory(i,j);
	    		   }
	    	   }
	    	   this.random_put_in_cache();
	    	  
	       }
	       private void random_put_in_cache()
	       {
	    	      for(int i=0;i<16;i++)
	    	      {
	    	    	  int x=(int)(Math.random()*16);
	    	    	  this.cache_memory_set.put(i, this.memory[i+x*16]);
	    	      }
	       }
	       private boolean find_memory_in_cache(Memory target_memory)
	       {
	    	   int arr_num=target_memory.get_Memory_arr_num();
	    	   Memory cache_memory=this.cache_memory_set.get(arr_num);
	    	   if(cache_memory.get_Memory_num()==target_memory.get_Memory_num())
	    	   {
	    		   System.out.println("target!!!");
	    		   return true;
	    	   }else{
	    		   System.out.println("is not target!!!");
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
