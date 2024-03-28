package com.educandoweb.cursospring.entities.enums;

public enum OrderStatus {

	// Os números 1,2,3,4,5 servem para identificar no banco de dados o que é cada coisa
	WAITING_PAGAMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	//codigo do tipo enumerado
	private int code;
	
	// o construtor do tipo enumerado é PRIVATE
	//o construtor recebe oos numeros que eu criei para definir no BD e tira o erro de cima
	private OrderStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	// static pois esse método vai funcionar sem precisar instanciar
	//converte o valor numerico para o tipo enumerado, vai retornar um obj do tipo OrderStatus e o codigo como argumento
	// ou seh=ja, vai passar um codigo e o metodo vai retornar o orderStatus correspondente a esse codigo, se passar 2 retorna 	PAID(2)
	public static  OrderStatus valueOf(int code) {
		// pecorrer todos os valores possiveis do orderStatus e para cada um deles testar se o código é o codigo correspondente, se sim retorna o valor
		for (OrderStatus value : OrderStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		// se não achar, lança dizendo que o codigo é invalido
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}
