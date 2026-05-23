import { HotelRepo } from '../repo/hotelRepo.ts';

export class HotelService {
	#hotelRepo: HotelRepo;
	constructor(hotelRepo: HotelRepo) {
		this.#hotelRepo = hotelRepo;
	}

	async getHotelById(hotelId: string) {
		const hotel = (await this.#hotelRepo.getHotelById(hotelId)) as any;

		if (!hotel) {
			throw new Error('Hotel not found');
		}

		return {
			hotelId: hotel.hotelid,
			name: hotel.name,
			city: hotel.city,
			totalRooms: hotel.totalrooms,
			availableRooms: hotel.availablerooms,
		};
	}

	async bookRooms(hotelId: string, bookedRooms: number) {
		const hotel = (await this.getHotelById(hotelId)) as any;

		if (bookedRooms <= 0) {
			throw new Error('Number of rooms to book must be greater than zero');
		}

		if (!hotel) {
			throw new Error('Hotel not found');
		}

		if (hotel.availablerooms < bookedRooms) {
			throw new Error('Not enough available rooms');
		}

		const updatedAvailableRooms = hotel.availableRooms - bookedRooms;
		console.log(updatedAvailableRooms);

		await this.#hotelRepo.updateAvailableRooms(hotelId, updatedAvailableRooms);
	}
}
