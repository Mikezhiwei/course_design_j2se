package method_two;

public class Memory {
         
	   /*
	    * 直接映射的主存只有11的地址号码
	    * */
	   private int memory_num;
	   
	  public Memory(int memory_num)
	  {
		  this.memory_num=memory_num;
	  }
	  public int get_memory_num()
	  {
		  return (this.memory_num);
	  }

}
