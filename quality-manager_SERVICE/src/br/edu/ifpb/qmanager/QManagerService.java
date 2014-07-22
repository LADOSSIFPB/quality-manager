package br.edu.ifpb.qmanager;

import javax.ws.rs.*;

@Path("service")
public class QManagerService {

	@GET
	@Path("helloworld")
	public String helloworld() {
		return "Hello World!";
	}

	@GET
	@Path("helloname/{name}")
	public String hello(@PathParam("name") final String name) {
		return "Hello " + name;
	}

	@GET
	@Path("item")
	@Produces({ "application/xml" })
	public Item getItem() {

		Item item = new Item();
		item.setDescription("produto1");
		item.setPrice(2500);

		return item;
	}

	@GET
	@Path("itens")
	@Produces({ "application/xml" })
	public Item[] getItens() {
		Item[] itens = new Item[2];
		Item item1 = new Item();
		item1.setDescription("produto1");
		item1.setPrice(2500);
		
		itens[0] = item1;
		
		Item item2 = new Item();
		item2.setDescription("produto2");
		item2.setPrice(3500);
		
		itens[1] = item2;

		return itens;
	}
}