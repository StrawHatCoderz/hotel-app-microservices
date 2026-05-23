import { Client } from '@db/postgres';

export const initDb = async (client: Client) => {
	await client.connect();
	console.log('Connected to PostgreSQL');

	const query = `CREATE TABLE IF NOT EXISTS hotel (
		hotelId SERIAL PRIMARY KEY,
		name VARCHAR(255) UNIQUE NOT NULL,
		city VARCHAR(255) NOT NULL,
		totalRooms INTEGER NOT NULL,
		availableRooms INTEGER NOT NULL
	)`;

	await client.queryArray(query);
};
