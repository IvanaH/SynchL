package thinkingIJ;

class FloatAssignment {
	public static void main(String[] args) {
		FloatDemo fd1 = new FloatDemo();
		FloatDemo fd2 = new FloatDemo();
		
		fd1.f = 12;
		fd2.f = 18;
		System.out.println("fd1.f = "+fd1.f +", fd2.f = "+fd2.f);
		
		fd1 = fd2;
		System.out.println("fd1.f = "+fd1.f +", fd2.f = "+fd2.f);
		
		fd1.f = 23;
		System.out.println("fd1.f = "+fd1.f +", fd2.f = "+fd2.f);
		
		fd1.fx(fd2);
		System.out.println("fd1.f = "+fd1.f +", fd2.f = "+fd2.f);
	}
}

class FloatDemo{
	float f;
	
	void fx(FloatDemo fd){
		fd.f = 0;
	}
}
