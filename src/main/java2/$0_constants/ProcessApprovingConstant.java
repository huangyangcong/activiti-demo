package $0_constants;

public interface ProcessApprovingConstant {
	
	public enum ApprovingTypeEnum {
		
		//10：同意，20：打回,90:终止；
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
