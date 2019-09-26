
interface Monitor<T> {
	T getHandler() throws Exception;
}

abstract class Benz<T> {
	public String openTurbo() {
		return "openTurbo";
	}
	public String stop() {
		return "stop";
	}
}

class C100 extends Benz implements Monitor {
	public String openHighMode() {
		return "openHighMode";
	}

	@Override
	public Object getHandler() throws Exception {
		return this.getClass();
	}
}

class C200 extends Benz implements Monitor {
	public String openSpeendMode() {
		return "openSpeendMode";
	}

	@Override
	public Object getHandler() throws Exception {
		return this.getClass();
	}
}

public class TestBenz {
	public static void main(String[] args) throws Exception {
		Monitor<Benz> c100 = new C100();
		System.out.println(c100.getHandler().openTurbo());
		System.out.println(c100);
	}
}
