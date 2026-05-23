import { Client } from '@db/postgres';

export class HotelRepo {
	#db: Client;

	constructor(db: Client) {
		this.#db = db;
	}

	async findByCity(city: string) {
		const query = `SELECT hotelId, name, city, totalRooms, availableRooms
      FROM hotel
      WHERE city = $1`;

		const result = await this.#db.queryArray(query, [city]);
		return result.rows;
	}

	async getHotelById(hotelId: string) {
		const query = `SELECT hotelId, name, city, totalRooms, availableRooms
			FROM hotel
			WHERE hotelId = $1`;

		const result = await this.#db.queryObject(query, [hotelId]);

		return result.rows[0];
	}

	async updateAvailableRooms(hotelId: string, bookedRooms: number) {
		const query = `UPDATE hotel
			SET availableRooms = $1
			WHERE hotelId = $2`;

		await this.#db.queryArray(query, [bookedRooms, hotelId]);
	}
}
