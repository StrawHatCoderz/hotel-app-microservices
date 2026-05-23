import { createApp } from './src/app.ts';
import { client } from './src/config/pgConfig.ts';
import { initDb } from './src/db/initDb.ts';
import { HotelRepo } from './src/repo/hotelRepo.ts';
import { HotelService } from "./src/service/HotelService.ts";
import { SearchService } from './src/service/SearchService.ts';

const main = async () => {
	await initDb(client);
	const hotelRepo = new HotelRepo(client);
	const searchService = new SearchService(hotelRepo);
	const hotelService = new HotelService(hotelRepo);

	const app = createApp({ searchService, hotelService });

	Deno.serve({ port: 3002 }, app.fetch);
};

main();
