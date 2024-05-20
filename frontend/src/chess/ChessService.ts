import axios from "axios";
import type {GameModel} from "@/chess/models/GameModel";
import type {RouteParamValue} from "vue-router";

export class ChessService {
    public static async createGame(): Promise<string>{
        try {
            const response = await axios.get('http://localhost:8080/chess')
            return response.data
        } catch (e) {
            return ""
        }
    }

    public static async getBoard(id: string): Promise<GameModel> {
        try {
            const response = await axios.get(
                `http://localhost:8080/chess/getGame`,
                {
                    params: {
                        id: id
                    }
                })
            console.log(response.data)
            return response.data
        }
        catch (e) {
            throw e
        }
    }
}