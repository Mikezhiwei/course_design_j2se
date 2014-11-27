package method_two;

import java.util.ArrayList;
import java.util.List;

public class Cache {
	   
        /*
         * 全相联映射，用数组容器来实现cache的数据结构
         * */
	    private List<Memory> cache_memory_set;//cache和主存的映射关系
	    
	    private Memory[] memory;
	    
	   	public Cache()
	    {
		   this.memory=new Memory[2048];
		   for(int memoryNumber=0;memoryNumber<this.memory.length;memoryNumber++)
		   {
			   this.memory[memoryNumber]=new Memory(memoryNumber);
		   }
		   this.cache_memory_set=new ArrayList<Memory>();
		   /*
		    * cache随机装入
		    * */
		   for(int index=0;index<16;index++)
		   {
			     int randomIndex=(int)(Math.random()*2048);
			     this.cache_memory_set.add(this.memory[randomIndex]);
		   }
	   }
	   
	   	public void find_in_cache(Memory target_memory)
	   	{
	   		  /*
	   		   * 全相联映射顺序查找cache,运行时间复杂度为O(n);
	   		   * */
	   		  int memory_num=target_memory.get_memory_num();
	   		  int index=0;
	   		  boolean istarget=false;
	   		  for(index=0;index<this.cache_memory_set.size();index++)
	   		  {
	   			     Memory cache_memory=this.cache_memory_set.get(index);
	   			     if(memory_num==cache_memory.get_memory_num())
	   			     {
	   			    	 System.out.println("CPU is target!!");
	   			    	 istarget=true;
	   			    	 break;
	   			     }
	   			   
	   		  }
	   		  if(!istarget)
	   		  {   
	   			     /*
	   			      * 未命中用队列算法装入
	   			      * */
	   			     System.out.println("CPU is  not target!!");
	   			     this.cache_memory_set.remove(0);
	   			     this.cache_memory_set.add(target_memory);
	   		  }
	   		  
	   	}
	   	
	   public static void main(String[] args) 
	   {
              Cache cache=new Cache();
              Memory memory=new Memory(18);
              cache.find_in_cache(memory);
              cache.find_in_cache(memory);
	
	   }
	  

}
