import { HotelRepo } from '../repo/hotelRepo.ts';

export class SearchService {
	#hotelRepo;

	constructor(hotelRepo: HotelRepo) {
		this.#hotelRepo = hotelRepo;
	}

	async findHotelsByCity(city: string) {
		return await this.#hotelRepo.findByCity(city);
	}
}
