import { HotelRepo } from '../repo/hotelRepo.ts';
import { CacheService } from './CacheService.ts';

export class SearchService {
	#hotelRepo;
	#cacheService;

	constructor(hotelRepo: HotelRepo, cacheService: CacheService) {
		this.#hotelRepo = hotelRepo;
		this.#cacheService = cacheService;
	}

	async findHotelsByCity(city: string) {
		const cachedHotels = await this.#cacheService.get(`hotels:${city}`);
		if (cachedHotels) {
			return cachedHotels;
		}

		const hotels = await this.#hotelRepo.findByCity(city);
		const ttl = Deno.env.get('CACHE_TTL')
			? parseInt(Deno.env.get('CACHE_TTL') as string, 10)
			: 60 * 3;

		await this.#cacheService.set(`hotels:${city}`, hotels, ttl);
		return hotels;
	}
}
