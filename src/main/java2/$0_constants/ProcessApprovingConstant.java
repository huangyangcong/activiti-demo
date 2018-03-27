package $0_constants;

public interface ProcessApprovingConstant {
	
	public enum ApprovingTypeEnum {
		
		//10��ͬ�⣬20�����,90:��ֹ��
		AGREE(10),
		BACK(20),
		TERMINAL(90);
		
		
		private int code;
		
		ApprovingTypeEnum(int code) {
			this.code = code;
		}
		
		public int getCode(){
			return this.code;
		}
	}
	
}
