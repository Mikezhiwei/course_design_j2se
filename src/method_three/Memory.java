package method_three;

public class Memory {
      
	private int memory_num;// 主存的组号
	private int memory_arr_num;// 组内序号

	public Memory(int memory_num, int memory_arr_num) {
		this.memory_num = memory_num;
		this.memory_arr_num = memory_arr_num;

	}

	public int get_Memory_num() {
		return (this.memory_num);
	}

	public int get_Memory_arr_num() {
		return (this.memory_arr_num);
	}

}
