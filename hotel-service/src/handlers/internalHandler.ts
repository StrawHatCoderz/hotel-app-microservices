import { Context } from 'hono';
import { BookRoomRequest } from '../../types/bookRoom.ts';
import { HotelService } from '../service/HotelService.ts';

export const serveHotelData = async (c: Context) => {
	const hotelId = c.req.param('hotelId');
	const hotelService = c.get('hotelService') as HotelService;

	if (!hotelId) {
		return c.json(
			{ success: false, error: { message: 'Hotel ID is required' } },
			400,
		);
	}

	try {
		const hotelData = await hotelService.getHotelById(hotelId);
		return c.json({ success: true, data: hotelData }, 200);
	} catch (error) {
		return c.json(
			{ success: false, error: { message: (error as Error).message } },
			404,
		);
	}
};

export const updateHotelData = async (c: Context) => {
	const hotelId = c.req.param('hotelId');
	const hotelService = c.get('hotelService') as HotelService;

	if (!hotelId) {
		return c.json(
			{ success: false, error: { message: 'Hotel ID is required' } },
			400,
		);
	}

	const bookedRooms: BookRoomRequest = await c.req.json();

	if (bookedRooms === undefined) {
		return c.json(
			{
				success: false,
				error: { message: 'Invalid number of rooms to update' },
			},
			400,
		);
	}

	try {
		await hotelService.bookRooms(hotelId, Number(bookedRooms));
		return c.json({ success: true }, 200);
	} catch (error) {
		return c.json(
			{ success: false, error: { message: (error as Error).message } },
			400,
		);
	}
};
