package at.decisionexpert.neo4jentity.node;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Tradeoff extends Node {
	
	@Relationship(type = "OVER", direction = Relationship.OUTGOING)
	private TradeoffItem tradeoffItemOver;

	@Relationship(type = "UNDER", direction = Relationship.OUTGOING)
	private TradeoffItem tradeoffItemUnder;

	public TradeoffItem getTradeoffItemOver() {
		return tradeoffItemOver;
	}

	public void setTradeoffItemOver(TradeoffItem tradeoffItemOver) {
		this.tradeoffItemOver = tradeoffItemOver;
	}

	public TradeoffItem getTradeoffItemUnder() {
		return tradeoffItemUnder;
	}

	public void setTradeoffItemUnder(TradeoffItem tradeoffItemUnder) {
		this.tradeoffItemUnder = tradeoffItemUnder;
	}

	public Tradeoff(User creator) {
		super(creator);
		// TODO Auto-generated constructor stub
	}

	public Tradeoff(User creator, TradeoffItem tradeoffItemOver, TradeoffItem tradeoffItemUnder) {
		super(creator);
		this.tradeoffItemOver = tradeoffItemOver;
		this.tradeoffItemUnder = tradeoffItemUnder;
	}
	
	public Tradeoff(TradeoffItem tradeoffItemOver, TradeoffItem tradeoffItemUnder) {
		super();
		this.tradeoffItemOver = tradeoffItemOver;
		this.tradeoffItemUnder = tradeoffItemUnder;
	}

	public Tradeoff() {
		super();
		// TODO Auto-generated constructor stub
	}	

}
